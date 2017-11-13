module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: 'lushx',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'A website for providing conveniences and happiness' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'stylesheet', href: '//cdn.bootcss.com/loaders.css/0.1.2/loaders.min.css' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#00FF99' },
  /**
   * Import plugins
   */
  plugins: [
    '~plugins/iview.js',
    '~plugins/lazyload.js'
  ],
  /**
   *  Router Middleware
   */
  router: {
    middleware: 'auth',
    scrollBehavior: (to, from, savedPosition) => {
      return { x: 0, y: 0 }
    }
  },
  /*
  ** Build configuration
  */
  build: {
    /**
     * Build one time
     */
    vendor: [
      'axios',
      'lodash',
      'iview',
      'store'
    ]
    /*
    ** Run ESLint on save
    */
    // extend (config, ctx) {
    //   if (ctx.dev && ctx.isClient) {
    //     config.module.rules.push({
    //       enforce: 'pre',
    //       test: /\.(js|vue)$/,
    //       loader: 'eslint-loader',
    //       exclude: /(node_modules)/
    //     })
    //   }
    // }
  }
}
