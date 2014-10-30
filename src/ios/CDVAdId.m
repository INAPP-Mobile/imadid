#import "CDVAdId.h"
#import <AdSupport/ASIdentifierManager.h>

@implementation CDVAdId

- (void) get:(CDVInvokedUrlCommand*)command {
  NSString *res = nil;
  CDVPluginResult* pluginResult = nil;

  if ([self getAdId:&res] == 0)
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                     messageAsString:res];
  else
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR
                                     messageAsString: res];
  
  [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (int) getAdId:(NSString**)aid {
  NSString *uuid = [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
  
  if (uuid) {
    *aid = uuid;
    return 0;
  }
  else {
    *aid = @"";
    return -1;
  }
}

@end