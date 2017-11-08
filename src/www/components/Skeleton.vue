<template lang="html">
  <section class="skeleton-container">
    <div class="skeleton-title"></div>
    <div class="skeleton-title--line"></div>
      <Row>
        <Col
          v-for="(item, idx) in 12"
          :key="idx"
          :xs="8" 
          :sm="6" 
          :md="4" 
          :lg="4"
        >
          <div class="skeleton-item"></div>
        </Col>
      </Row>
  </section>
</template>


<style lang="less" scoped>
  @skeleton-bg-color: #EFEFF2;
  .skeleton-container {
    padding-top: 100px;
    padding-bottom: 100px;
    width: 100%;
    min-height: 100vh;
  }

  .skeleton-title {
    margin-left: 16px;
    width: 200px;
    height: 40px;
    background-color: #EFEFF2;
  }

  .skeleton-title--line {
    margin-left: 16px;
    margin-top: 20px;
    width: 200px;
    height: 4px;
    background-color: #EFEFF2;
  }

  .skeleton-item {
    margin-top: 50px;
    margin-left: auto;
    margin-right: auto;
    width: 80%;
    height: 160px;
    background-color: #EFEFF2;
  }
</style>
