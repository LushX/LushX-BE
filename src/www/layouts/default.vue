<template>
  <div>
    <lushx-header></lushx-header>
    <nuxt/>
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
