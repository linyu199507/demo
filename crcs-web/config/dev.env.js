var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
    NODE_ENV: '"development"',
    REPORT_URL: '"http://127.0.0.1:8083/crcs-report"',
    AICORG_URL: '"http://localhost:8082/aicorg"',
Download_URL: '"https://www.chinaweal.com.cn/crcs-upload"'
    
})
