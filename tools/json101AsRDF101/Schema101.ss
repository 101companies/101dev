
    // schema of the 101 companies in the SimpleGraph Schema Notation
    // attribute can be removed if only some parts are to be extracted.
    // Names should be the same as those in the json files
    
    feature {
      name:string@ ;
      summary:string! ;
      description:string? ;
      illustration:string? ;
      implementations:implementation*
    }
    implementation {
      name:string@ ;
      summary:string! ;
      motivation:string! ;
      features:feature* ;
      languages:language* ;
      technologies:technology* ;
      usage:string?
    }
    language {
      name:string@ ;
      summary:string! ;
      description:string? ;
      implementations:implementation*
    }
    technology {
      name:string@ ; 
      summary:string! ; 
      description:string? ; 
      implementations:implementation*
    }


