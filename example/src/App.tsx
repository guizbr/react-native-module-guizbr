import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { isHeadphonesConnected, multiply } from 'react-native-module-guizbr';

export default function App() {
  const [result, setResult] = React.useState<any>();

  function teste() {
    isHeadphonesConnected()
      .then((connected) => {
        setResult(connected);
      })
      .catch((error) => {
        console.error('Erro ao verificar conexão do fone de ouvido:', error);
        setResult('Erro ao verificar conexão do fone de ouvido');
      });
  }

  function teste2() {
    multiply(1, 2)
      .then((val) => setResult(val))
      .catch((error) => {
        console.error('Erro ao verificar conexão do fone de ouvido:', error);
        setResult('Erro ao verificar conexão do fone de ouvido');
      });
  }

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={() => teste()}>
        <Text style={{ color: '#000' }}>BOTAO</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => teste2()}>
        <Text style={{ color: '#000' }}>BOTAO2</Text>
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
