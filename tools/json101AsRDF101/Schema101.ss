// schema of the 101 companies in the SimpleGraph Schema Notation (see megalib/SimpleGraph.php)
// @ means key
// ! means 1
// ? means 0..1
// * means *
// This file is used in the context of the transformation json101AsRdf101
// Only the class and attributes listed below will be converted
// There could be more in the original json file.
// Names should be the same as those in the json files.
    
    concept {
      name:string@ ;
      intent:string? ;
      // discussion:string?
    }
    
    category {
      name:string@ ;
      intent: string? ; 
      // discussion:string? ;
      categories:category* ;
      // members:  ????
    }
    
    feature {
      name:string@ ;
      summary:string! ;
      // description:string? ;
      // illustration:string? ;
      implementations:implementation*
    }
    
    implementation {
      name:string@ ;
      summary:string! ;
      // motivation:string! ;
      features:feature* ;
      languages:language* ;
      technologies:technology* ;
      // usage:string?
    }
    
    language {
      name:string@ ;
      summary:string! ;
      // description:string? ;
      implementations:implementation*
    }
    
    technology {
      name:string@ ; 
      summary:string! ; 
      // description:string? ; 
      implementations:implementation*
    }


