<?php
/**
 * botclasses.php - Bot classes for interacting with mediawiki.
 *
 *  (c) 2009-2010 Chris G - http://en.wikipedia.org/wiki/User:Chris_G
 *  (c) 2010      Fale - http://en.wikipedia.org/wiki/User:Fale
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *  Developers (add your self here if you worked on the code):
 *      Chris   - [[User:Chris_G]] - Wrote the whole code
 *      Fale    - [[User:Fale]]    - Code polish, docs
 **/

/** TODO: Write more functions, add some comments + documentation **/

class mediawiki
{
    protected $username;    /* @string - The bot's username (if it has one). */
    protected $password;    /* @string - The bot's password (if it has one). */
    protected $loggedin;    /* @bool   - Is the bot logged in? */
    protected $ectimestamp; /* @string - Timestamp, used to detect edit conflicts. */
    protected $edittoken;   /* @string - Our edit token (see http://www.mediawiki.org/wiki/Manual:Edit_token). */
    protected $url;         /* @string - The url to api.php. */
    protected $lasterror;   /* @string - Contains the last error the bot had. */
    protected $maxlag;      /* @int    - The maxlag level to use when making an edit. */
    public $http;           /* @class  - Our http client. */

    /**
     * @desc Our constructor. Sets up the http client for us to use and sets up all the variables. Will also log the user in if a username and password are suppied.
     * @param string (default=http://en.wikipedia.org/w/api.php) $url - A url to mediawiki's api.php.
     * @param string (default=null) $username  - The account's username.
     * @param string (default=null) $password  - The account's password.
     * @param int (default=0) $maxlag - The maxlag level to use when making an edit.
     */
    public function __construct ( $url = 'http://en.wikipedia.org/w/api.php', $username = null, $password = null, $maxlag = 0 )
    {
        // 101companies: renaming http to http2 to avoid name clashed with
        // the other bot
        // [begin]
        require_once 'http2.php';
        $this->http = new http2();
        // [end]
        $this->http->useragent = 'PHP Mediawiki Client';
        
        $this->ectimestamp = null;
        $this->edittoken   = null;
        $this->username    = $username;
        $this->password    = $password;
        $this->loggedin    = false;
        $this->url         = $url;
        $this->lasterror   = null;
        $this->maxlag      = $maxlag;
        
        if ( $username != null && $password != null )
        {
            $return = $this->login( $username, $password );
            if ( ! $return )
                die( 'Error logging in: ' . $this->lasterror );
        }
    }

    /**
     * @desc Returns the last error the script ran into.
     * @returns string
     */
    public function lasterror ()
    {
        return $this->lasterror;
    }

    /**
     * @desc Returns the current maxlag setting for the bot.
     * @return int
     */
    public function getmaxlag ()
    {
        return $this->maxlag;
    }

    /**
     * @desc Changes the bots maxlag setting.
     * @param int $maxlag - The maxlag number to change to (set to 0 to disable) (default = 0).
     */
    public function setmaxlag ( $maxlag )
    {
        $this->maxlag = $maxlag;
    }

    /**
     * @desc A wrapper function. Sends a query to the api, handles format itself.
     * @param string $query - The api query (e.g. '?action=help'), do not specify '&format=php' as this is added by the function.
     * @param array (default=null) $post - Any post data to be sent (e.g. for login requests).
     * @return array - Returns the api result.
     */
    public function query ( $query, $post = null )
    {
        if ( $post == null )
            $data = $this->http->get( $this->url . $query . '&format=php' );
        else
            $data = $this->http->post( $this->url . $query . '&format=php', $post );
        return unserialize( $data );
    }

    /**
     * @desc Simliar to query, however it automagicly supports api queries that use query-continue.
     * @param string $query - The api query.
     * @param int (default=0) $sleep  - How long to sleep for between queries (set to 0 to disable).
     * @return array - Returns the api result.
     */
    public function querycontinue ( $query, $sleep = 0 )
    {
        $continue = '';
        while ( true )
        {
            $x = $this->query( $query . $continue );
            $a = key( $x['query'] );
            if ( ! isset( $return ) )
                $return = $x;
            else
                $return['query'][$a] = array_merge( $return['query'][$a], $x['query'][$a] );
            if ( isset( $x['query-continue'] ) )
            {
                $b = key( $x['query-continue'][$a] );
                $continue = '&' . $b . '=' . urlencode( $x['query-continue'][$a][$b] );
            } else
                break;
            if ( $sleep != 0 )
                sleep( $sleep );
        }
        return $return;
    }

    /**
     * @desc Logs the account into mediawiki.
     * @param string $username - The account's username.
     * @param string $password - The account's password.
     * @returns bool - Returns true on success, false on failure.
     */
    public function login ( $username, $password )
    {
        $post = array(
                      'lgname'     => $username,
                      'lgpassword' => $password
                     );
    	while (true) {
        	$return = $this->query( '?action=login', $post );
        	if ( $return['login']['result'] == 'Success' )
        	{
            		$this->loggedin = true;
            		return true;
        	}
        	elseif ( $return['login']['result'] == 'NeedToken' ) 
        	{
			$post['lgtoken'] = $return['login']['token'];
        	}
        	else {
        		$this->lasterror = $return['login']['code'];
        		return false;
        	}
        }
    }

    /**
     * @desc Logs the account out of the wiki and destroys all their session data.
     */
    public function logout ()
    {
        $this->query( '?action=logout' );
        $this->ectimestamp = null;
        $this->edittoken   = null;
        $this->lasterror   = null;
        $this->loggedin    = false;
    }

    /**
     * @desc Returns the wiki text of a page.
     * @param string $page - The page you want to get the text of.
     * @param bool $useForEditConflict (default=false) - Should the bot use the timestamp from this revision to detect an edit conflict when editing?
     */
    public function getpage ( $page, $useForEditConflict = false )
    {
        $x = $this->query( '?action=query&prop=revisions&titles=' . urlencode($page) . '&rvprop=timestamp|content&rvlimit=1' );
        $id = key( $x['query']['pages'] );
        if ( isset( $x['query']['pages'][$id]['revisions'][0]['*'] ) )
        {
            if ($useForEditConflict)
                $this->ectimestamp = str_replace( array( 'T', 'Z' ), array( ' ', '' ), $x['query']['pages'][$id]['revisions'][0]['timestamp'] );
            return $x['query']['pages'][$id]['revisions'][0]['*'];
        }
        
        return false;
    }

    /**
     * @desc Returns the bot's edit token.
     * @param bool (default=false) $force - Force the script to get a fresh edit token.
     * @returns mixed - Returns the account's edittoken on success or false on failure.
     */
    public function getedittoken ( $force = false )
    {
        if ( $this->edittoken != null && $force == false )
            return $this->edittoken;
        $x = $this->query( '?action=query&prop=info&intoken=edit&titles=Main+Page' );
        @$id = key( $x['query']['pages'] );
        if ( isset( $x['query']['pages'][$id]['edittoken'] ) )
            return $x['query']['pages'][$id]['edittoken'];

        $this->lasterror = 'notoken';
        return false;
    }

    /**
     * @desc Purges the cache for a page.
     * @param string $page - The page to purge.
     */
    public function purge ( $page )
    {
        $this->query( '?action=purge', array( 'titles' => $page ) );
    }

    /**
     * @desc Edits a page.
     * @param string $title - The title of the page to edit.
     * @param string $content - The page content.
     * @param string (default='') $editsummary - The edit summary to use.
     * @param bool (default=false) $minor - Mark the edit as minor?
     * @param bool (default=true) $bot - Mark the edit as a bot edit?
     * @param bool (default=true) $retry - If the edit fails should the bot retry (used internaly).
     * @returns bool - Returns true on sucess, false on failure.
     */
    public function edit ( $title, $content, $editsummary = '', $minor = false, $bot = true, $retry = true )
    {
        $post = array(
                      'title'   => $title,
                      'text'    => $content,
                      'md5'     => md5( $content ),
                      'summary' => $editsummary,
                      'token'   => $this->getedittoken()
                     );
        if ( $minor )
            $post['minor'] = true;
        if ( $bot )
            $post['bot'] = true;
        if ( $this->ectimestamp != null )
        {
            $post['basetimestamp'] = $this->ectimestamp;
            $this->ectimestamp = null;
        }
        $x = $this->query( '?action=edit', $post );
        
        if ( ! isset( $x['error'] ) )
            return true;
        
        if ( $x['error']['code'] == 'badtoken' && $retry )
        {
            /* Did we use a bad token? Try editing again with a fresh token. */
            $this->getedittoken( true );
            return $this->edit( $title, $content, $editsummary, $minor, $bot, false );
        }
        $this->lasterror = $x['error']['code'];
        return false;
    }

    /**
     * @desc Reverts a user's edits to a page.
     * @param string $user - The user to revert.
     * @param string $page - The page the user edited.
     * @param bool $markbot (default=false) - Mark the revert as a bot edit. 
     * @param string $summary (default=null) - The revert summary, if set to null the default summary will be used.
     * @returns bool - true on success, false on error.
     */
    public function rollback ( $user, $page, $markbot = false, $summary = null )
    {
        $token = $this->getrollbacktoken( $user, $page );
        if ( $token == false )
            return false;
        
        $post = array(
                      'title' => $page,
                      'user'  => $user,
                      'token' => $token
                     );
        if ( $markbot )
            $post['markbot'] = true;
        if ($summary != null)
            $post['summary'] = $summary;
        $x = $this->query( '?action=rollback', $post );
        if ( ! isset( $x['error'] ) )
            return true;

        $this->lasterror = $x['error']['code'];
        return false;
    }

    /**
     * @desc Returns a rollback token for use in a rollback request.
     * @param string $user - The user to revert.
     * @param string $page - The page the user edited.
     * @returns mixed - Returns the rollback token on success, or false on failure.
     */
    protected function getrollbacktoken ( $user, $page )
    {
        $x = $this->query( '?action=query&prop=revisions&rvprop=user&rvtoken=rollback&titles=' . urlencode( $page ) );
        @$id = key( $x['query']['pages'] );
        if ( isset( $x['query']['pages'][$id]['revisions'][0]['rollbacktoken'] ) && $x['query']['pages'][$id]['revisions'][0]['user']==$user )
            return $x['query']['pages'][$id]['revisions'][0]['rollbacktoken'];

        $this->lasterror = 'notoken';
        return false;
    }
    
    /**
     * @desc Deletes a page.
     * @param string $page - The page to delete.
     * @param string $reason (default=null) - The reason for deleting the page (if set to null an automatically generated reason will be used.).
     * @param bool $retry (default=true) - If the edit fails should the bot retry (used internaly).
     * @returns bool - Returns true on success, false on error.
     */
    public function delete ( $page, $reason = null, $retry = true )
    {
        $post = array(
                      'title' => $page,
                      'token' => $this->getedittoken()
                     );
        if ( $reason != null )
            $post['reason'] = $reson;
        $x = $this->query( '?action=delete', $post );
        if (!isset($x['error']))
            return true;

        if ( $x['error']['code'] == 'badtoken' && $retry )
        {
            /* Did we use a bad token? Try editing again with a fresh token. */
            $this->getedittoken(true);
            return $this->delete($page,$reason,false);
        }
        $this->lasterror = $x['error']['code'];
        return false;
    }

    /**
     * @desc Our destructor, logs the account out if it is still logged in.
     */
    public function __destruct ()
    {
        if ( $this->loggedin )
            $this->logout();
    }
}

?>
