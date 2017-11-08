<template>
  <div>
    <lushx-header></lushx-header>
    <nuxt/>
    <BackTop :bottom=6 :right=6></BackTop>
    <lushx-footer></lushx-footer>
  </div>
</template>

<script>
  import LushxHeader from '~/components/Header'
  import LushxFooter from '~/components/Footer'

  export default {
    components: {
      LushxHeader,
      LushxFooter
    }
  }
</script>
