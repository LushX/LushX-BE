<template>
  <div>
    <nuxt/>
    <BackTop :bottom=6 :right=6></BackTop>
    <lushx-footer></lushx-footer>
  </div>
</template>

<script>
  import LushxFooter from '~/components/Footer'

  export default {
    components: {
      LushxFooter
    }
  }
</script>
