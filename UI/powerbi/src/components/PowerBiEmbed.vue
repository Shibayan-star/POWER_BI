<template>
  <div>
    <button @click="EmbedReport">Initialize Data</button>
  </div>
</template>

<script>
import axios from 'axios';
import * as pbi from "powerbi-client";

export default {
  name: "PowerBIEmbed",
  data() {
    return {
      report: null,
      embedConfig: null // Initializing to null initially
    };
  },
  methods: {
    async EmbedReport() {
      // Initialize the embedConfig object here
      const response = await axios.get('http://localhost:8048/v1/getEmbedToken');
      const embedUrl = response.data;
      this.embedConfig = {
        type: 'report',
        tokenType: pbi.models.TokenType.Embed,
        accessToken: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IlhSdmtvOFA3QTNVYVdTblU3Yk05blQwTWpoQSIsImtpZCI6IlhSdmtvOFA3QTNVYVdTblU3Yk05blQwTWpoQSJ9.eyJhdWQiOiJodHRwczovL2FuYWx5c2lzLndpbmRvd3MubmV0L3Bvd2VyYmkvYXBpIiwiaXNzIjoiaHR0cHM6Ly9zdHMud2luZG93cy5uZXQvOTY1MmQ3YzItMWNjZi00OTQwLTgxNTEtNGE5MmJkNDc0ZWQwLyIsImlhdCI6MTcxMTk3ODkwNSwibmJmIjoxNzExOTc4OTA1LCJleHAiOjE3MTE5ODI4MDUsImFpbyI6IkUyTmdZQ2hjL1NSNmt0R3UwcTdFc0xWVEorbzhBQUE9IiwiYXBwaWQiOiIyMDI5OThhZS1mYWIyLTQ1YzEtYmJlMC1iNWNlN2MyNDRkODciLCJhcHBpZGFjciI6IjEiLCJpZHAiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC85NjUyZDdjMi0xY2NmLTQ5NDAtODE1MS00YTkyYmQ0NzRlZDAvIiwiaWR0eXAiOiJhcHAiLCJvaWQiOiI5OWIyMTQzNS1mNWYxLTQ5YjctYjYxNS1lYTQ4N2JjMDcwMTYiLCJyaCI6IjAuQVFJQXd0ZFNsczhjUUVtQlVVcVN2VWRPMEFrQUFBQUFBQUFBd0FBQUFBQUFBQUFDQUFBLiIsInN1YiI6Ijk5YjIxNDM1LWY1ZjEtNDliNy1iNjE1LWVhNDg3YmMwNzAxNiIsInRpZCI6Ijk2NTJkN2MyLTFjY2YtNDk0MC04MTUxLTRhOTJiZDQ3NGVkMCIsInV0aSI6IjVIaUNmNk9NMWstejFEMnV0M0lDQUEiLCJ2ZXIiOiIxLjAifQ.eh3HPnnT-CZHKr0ffak12vrWvqvqqsLmMSyPfv8PmZ9Gpoj3Dj96TcIJiZ0sbvjIEIp6NTJMUpLCh8fI4HbgJjgsxYWvmXiQsSh9sszgN7mSAqs1rtm2--cYPvUQhn8EDuCDoB2RKrIt0cb7kcWoyeJmtrbYBwaRImBTE46UEr3gMuTVsXnbXeMwJ-VekUnG3JLHS_T9_8xfr6NCBF_4FhnXvfjwERIUluw8x0rvbBro5vyPcIpXXFs6vZSmTLSX1ic5yQqzaq1BEbY_pWIWpz5fzqibbFVHnxDPa6CEFaK-AIw37yo24n_lVNmECaGjQzzNMjIQU6sxmDIKVyxp2w', // Replace with your access token
        embedUrl: embedUrl,
        id: 'cd026166-bcd5-4cb6-ac44-edbda2d78829',
        permissions: pbi.models.Permissions.All,
        settings: {
          filterPaneEnabled: true,
          navContentPaneEnabled: true
        }
      };

      // Embed the report
      const embedContainer = this.$refs.embeddedReport;
      this.report = pbi.Embed(embedContainer, this.embedConfig);
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
