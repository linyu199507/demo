// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')

module.exports = {
    build: {
        env: require('./prod.env'),
        index: path.resolve(__dirname, '../dist/index.html'),
        assetsRoot: path.resolve(__dirname, '../dist'),
        assetsSubDirectory: 'static',
        assetsPublicPath: '/crcs-web/',
        productionSourceMap: true,
        // Gzip off by default as many popular static hosts such as
        // Surge or Netlify already gzip all static assets for you.
        // Before setting to `true`, make sure to:
        // npm install --save-dev compression-webpack-plugin
        productionGzip: false,
        productionGzipExtensions: ['js', 'css'],
        // Run the build command with an extra argument to
        // View the bundle analyzer report after build finishes:
        // `npm run build --report`
        // Set to `true` or `false` to always turn it on or off
        bundleAnalyzerReport: process.env.npm_config_report
    },
    dev: {
        env: require('./dev.env'),
        port: 8080,
        autoOpenBrowser: true,
        assetsSubDirectory: 'static',
        assetsPublicPath: '/',
        // proxyTable: {},
        proxyTable: {
            '/crcs':{
                // target:'http://172.22.80.37:8888',  //这里配置的是 请求接口的域名
                target:'http://127.0.0.1:8081',  //这里配置的是 请求接口的域名
                // target:'http://172.22.80.37:8081',  //这里配置的是 请求接口的域名
                // target:'http://192.168.100.19:8888',  //这里配置的是 请求接口的域名
                secure: false, // 如果是https接口，需要配置这个参数
                changeOriginL: true, // 如果接口跨域，需要进行这个参数配置
                // pathRewrite:{
                //     '^/crcs':'/crcs-develop' //路径重写，这里理解成用'/api'代替target里面的地址，比如我们调用‘http://10.1.0.34:8000/api/homepage’，直接写‘/api/homepage’即可。
                // }
            },
        },
        // CSS Sourcemaps off by default because relative paths are "buggy"
        // with this option, according to the CSS-Loader README
        // (https://github.com/webpack/css-loader#sourcemaps)
        // In our experience, they generally work as expected,
        // just be aware of this issue when enabling this option.
        cssSourceMap: false
    }
}
