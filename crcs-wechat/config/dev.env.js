'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  REPORT_URL: '"http://127.0.0.1:8082/crcs-report"',
  AICORG_URL: '"http://localhost:8082/aicorg"',
  Download_URL: '"https://www.chinaweal.com.cn/crcs-upload"'
})
