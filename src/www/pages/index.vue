<template>
  <section class="container">
    <div>
      <h1 class="title">
        lushx
      </h1>
      <h2 class="subtitle">
        A website for providing conveniences and happiness
      </h2>
    </div>
  </section>
</template>

<script>
  import ajax from '../server/axios.config.js'
  import url from '../server/url.js'
  export default {
    name: "Auth",
    data() {
      return {

      }
    },
    mounted () {
      ajax.get({
        url: url.INDEX_VEDIO
      }).then((data) => {
        console.log(data)
      })
    }
  }
</script>

<style>
.container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}
</style>
