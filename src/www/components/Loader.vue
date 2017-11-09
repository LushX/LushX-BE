<template lang="html">
  <section class="loader-container">
    <div class="loader">
      <div class="pacman">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>
  </section>
</template>


<style lang="less" scoped>
  .loader-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100vh;
    background: #38485C;
    z-index: 999999998;
  }

  .loader {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    box-sizing: border-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex: 0 1 auto;
    flex: 0 1 auto;
    -ms-flex-direction: column;
    flex-direction: column;
    -ms-flex-positive: 1;
    flex-grow: 1;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    -ms-flex-preferred-size: 25%;
    flex-basis: 25%;
    max-width: 25%;
    height: 200px;
    -ms-flex-align: center;
    align-items: center;
    -ms-flex-pack: center;
    justify-content: center;
  }

  .pacman {
    position: relative;
  }

  .pacman>div:first-of-type {
    -webkit-animation: rotate_pacman_half_up .5s 0s infinite;
    animation: rotate_pacman_half_up .5s 0s infinite;
  }

  .pacman>div:first-of-type, .pacman>div:nth-child(2) {
    width: 0;
    height: 0;
    border-right: 25px solid transparent;
    border-top: 25px solid #fff;
    border-left: 25px solid #fff;
    border-bottom: 25px solid #fff;
    border-radius: 25px;
    position: relative;
    left: -30px;
  }

  .pacman>div:nth-child(2) {
      -webkit-animation: rotate_pacman_half_down .5s 0s infinite;
      animation: rotate_pacman_half_down .5s 0s infinite;
      margin-top: -50px;
  }

  .pacman>div:first-of-type, .pacman>div:nth-child(2) {
    width: 0;
    height: 0;
    border-right: 25px solid transparent;
    border-top: 25px solid #fff;
    border-left: 25px solid #fff;
    border-bottom: 25px solid #fff;
    border-radius: 25px;
    position: relative;
    left: -30px;
  }

  .pacman>div:nth-child(3), .pacman>div:nth-child(4), .pacman>div:nth-child(5), .pacman>div:nth-child(6) {
    background-color: #fff;
    border-radius: 100%;
    margin: 2px;
    width: 10px;
    height: 10px;
    position: absolute;
    -webkit-transform: translate(0,-6.25px);
    transform: translate(0,-6.25px);
    top: 25px;
    left: 70px;
  }

  .pacman>div:nth-child(3) {
    -webkit-animation: pacman-balls 1s -.66s infinite linear;
    animation: pacman-balls 1s -.66s infinite linear;
  }

  .pacman>div:nth-child(4) {
    -webkit-animation: pacman-balls 1s -.33s infinite linear;
    animation: pacman-balls 1s -.33s infinite linear;
  }

  .pacman>div:nth-child(5) {
    -webkit-animation: pacman-balls 1s 0s infinite linear;
    animation: pacman-balls 1s 0s infinite linear;
  }
</style>
