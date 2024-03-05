import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { isHeadphonesConnected } from 'react-native-module-guizbr';

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();

  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={async () => {
          const x = await isHeadphonesConnected();
          setResult(x);
        }}
      >
        <Text style={{ color: '#000' }}>BOTAO</Text>
      </TouchableOpacity>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
