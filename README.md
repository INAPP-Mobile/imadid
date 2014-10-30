# imadid

Mobile ad id retriever


# Installation

```
$ npm install imadid
```

# Usage
```

var adid = require('imadid');
var b = IMAdId();

b.get(function(error, response, body){

	if ( !error ) {
        console.log(body);
    }
	else
		console.log(error);
});

```

# GIT

https://github.com/INAPP-Mobile/imadid.git

# License

MIT
