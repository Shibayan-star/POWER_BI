<template>
  <div>
    <button @click="fetchUrlAndLaunch">Launch PowerBi</button>
  </div>
</template>

<script>
import axios from 'axios';
import * as pbi from 'powerbi-client-vue-js';

export default {
  name: 'HelloWorld',
  data() {
    return {
      powerbi: pbi
    };
  },
  methods: {
    embedReport(embedContainer, config) {
      pbi.PowerBIReportEmbed
    },
    data() {
      return {
        report: null,
        embedConfig: {
          type: 'report',
          tokenType: pbi.models.TokenType.Embed,
          accessToken: 'YOUR_ACCESS_TOKEN', // Replace with your access token
          embedUrl: 'YOUR_EMBED_URL', // Replace with your embed URL
          id: 'YOUR_REPORT_ID', // Replace with your report ID
          permissions: pbi.models.Permissions.All,
          settings: {
            filterPaneEnabled: true,
            navContentPaneEnabled: true
          }
        }
      };
    },
    async fetchUrlAndLaunch() {
      try {
        const response = await axios.get('http://localhost:8048/v1/getEmbedToken');
        const embedUrl = response.data;
        const embedContainer = this.$refs.reportContainer;

        const config = {
          type: 'report',
          tokenType: pbi.models.TokenType.Embed,
          embedUrl: embedUrl,
          id: 'cd026166-bcd5-4cb6-ac44-edbda2d78829',
          permissions: pbi.models.Permissions.All,
          settings: {
            filterPaneEnabled: true,
            navContentPaneEnabled: true
          }
        };

        this.embedReport(embedContainer, config);
      } catch (error) {
        console.error('Error embedding report:', error);
      }
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

