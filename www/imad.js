
cordova.define("com.ios.cordova-idfa.getIDFA", function(require, exports, module) {
    var exec = require('cordova/exec');

    var getIDFA = function (success, failure) {
        exec(success, failure, "CDVAdId", "get", []);
    };

    module.exports = getIDFA;
});