import { NativeModules, Platform } from 'react-native';

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

export function openCamera(): Promise<any> {
  return ModuleGuizbr.openCamera();
}
