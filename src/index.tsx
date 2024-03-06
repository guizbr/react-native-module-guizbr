import {
  NativeModules,
  Platform,
  NativeEventEmitter,
  type EmitterSubscription,
} from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-module-guizbr' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const ModuleGuizbr = NativeModules.ModuleGuizbr
  ? NativeModules.ModuleGuizbr
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const eventEmitter = new NativeEventEmitter(ModuleGuizbr);

let subscription: EmitterSubscription | null;

export function multiply(a: number, b: number): Promise<number> {
  return ModuleGuizbr.multiply(a, b);
}

export function isHeadphonesConnected(): Promise<any> {
  return ModuleGuizbr.isHeadphonesConnected();
}

export function subscribeToMediaButtonEvent(callback: (event: any) => void) {
  if (!subscription) {
    ModuleGuizbr.registerMediaButtonEvent();
    subscription = eventEmitter.addListener('MediaButtonClicked', callback);
  }
}

export function unsubscribeFromMediaButtonEvent() {
  if (subscription) {
    subscription.remove();
    subscription = null;
    ModuleGuizbr.unregisterMediaButtonEvent();
  }
}
