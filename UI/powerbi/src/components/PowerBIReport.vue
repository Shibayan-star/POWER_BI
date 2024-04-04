<template>
  <div>
    <iframe ref="embeddedReport" style="width: 100%; height: 100px;"></iframe>
    <button @click="loadReport">Load Report</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PowerBIReport",
  methods: {
    async loadReport() {
      const response = await axios.get('http://localhost:8048/v1/getEmbedToken');
      // Replace 'YOUR_EMBED_URL' with the embed URL provided by the backend
      const embedUrl = response.data;
      const iframe = this.$refs.embeddedReport;

      // Set the src attribute of the iframe to the embed URL
      iframe.src = embedUrl;
    }
  }
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f0f0;
}

.container {
  text-align: center;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}
</style>
