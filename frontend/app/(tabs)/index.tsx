import { StyleSheet, View, Text } from 'react-native';

import { useEffect, useState } from 'react';
import { FlatList, Swipeable } from 'react-native-gesture-handler';
import { Viagem } from '@/constants/viagem';
import { SafeAreaView } from 'react-native-safe-area-context';
import { SwipeListView, SwipeRow } from 'react-native-swipe-list-view';

const API = 'http://localhost:8080/api/viagens'

export function useViagens() {
  const [viagens, setViagens] = useState<Viagem[]>([]);

  useEffect(() => {
    async function fetchViagens() {
      try {
        const response = await fetch(API, {
          method: "GET"
        });
        const json = await response.json();
        console.log(json)
        setViagens(json);
      } catch (error) {
        console.log(error);
      }
    }

    fetchViagens();
  }, []);
  console.log(viagens)
  return viagens;
}

export async function deleteById(id: number) {
  fetch(`${API}/${id}`, { method: 'DELETE' });
  useViagens();
}

const myItemSeparator = () => {
  return <View style={{ height: 1, backgroundColor: "grey", marginHorizontal: 10 }} />;
};

const myListEmpty = () => {
  return (
    <View style={{ alignItems: "center" }}>
      <Text style={styles.item}>No data found</Text>
    </View>
  );
};

export default function HomeScreen() {
  const viagens = useViagens();
  return (
    <SafeAreaView style={styles.container}>
      <SwipeListView
        keyExtractor={(item) => item.id.toString()}
        data={viagens
        }
        renderItem={({ item }) => (
          <Text style={styles.item}>{item.nome}</Text>
        )}
        ItemSeparatorComponent={myItemSeparator}
        ListEmptyComponent={myListEmpty}
        ListHeaderComponent={() => (
          <Text style={{ fontSize: 30, textAlign: "center", marginTop: 20, fontWeight: 'bold', textDecorationLine: 'underline' }}>
            Lista de Viagens
          </Text>
        )}
        leftActivationValue={70}
        onLeftAction={(item, rowMap) => console.log(item, rowMap)}

      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 50,
    flex: 1,
  },
  item: {
    padding: 20,
    fontSize: 15,
    marginTop: 5,
    textAlign: 'center'
  },
});