<template>
  <div>
    <h1>실시간 주식 정보</h1>
    <table>
      <thead>
      <tr>
        <th>종목명</th>
        <th>현재가</th>
        <th>대비</th>
        <th>거래량</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="stock in stocks" :key="stock.name">
        <td>{{ stock.name }}</td>
        <td>{{ stock.price }}</td>
        <td :class="{ 'up': stock.change > 0, 'down': stock.change < 0 }">
          {{ stock.change > 0 ? '+' : '' }}{{ stock.change }}
        </td>
        <td>{{ stock.volume }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { ref, onMounted, onUnmounted } from 'vue'

export default {
  setup() {
    const stocks = ref([])
    let eventSource

    const updateStock = (data) => {
      const index = stocks.value.findIndex(s => s.name === data.name)
      if (index !== -1) {
        stocks.value[index] = data
      } else {
        stocks.value.push(data)
      }
    }

    onMounted(() => {
      eventSource = new EventSource('http://localhost:8080/stocks')
      eventSource.onmessage = (event) => {
        const data = JSON.parse(event.data)
        updateStock(data)
      }
    })

    onUnmounted(() => {
      if (eventSource) {
        eventSource.close()
      }
    })

    return {
      stocks
    }
  }
}
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
th {
  background-color: #f2f2f2;
}
.up {
  color: green;
}
.down {
  color: red;
}
</style>