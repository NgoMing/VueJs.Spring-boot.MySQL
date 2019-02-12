module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8090'
      }
    }
  },
  configureWebpack: {
    devtool: 'source-map',
    entry: {
      app: './src/main.js',
      style: [
        'bootstrap/dist/css/bootstrap.min.css'
      ]
    }
  }
}
