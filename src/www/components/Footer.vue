<template lang="html">
  <footer>
    <Row :gutter="16">
      <Col :xs="24" :sm="12" :md="12" :lg="8">
        <ul class="footer-wrapper">
          <li class="footer-item title">
            <Icon type="social-github"></Icon>
            GitHub
          </li>
          <li v-for="repositorie in repositories" :key="repositorie.name" class="footer-item">
            <a :href="repositorie.url" target="_blank">{{ repositorie.name }}</a>
          </li>
        </ul>
      </Col>
      <Col :xs="24" :sm="12" :md="12" :lg="8">
        <ul class="footer-wrapper">
          <li class="footer-item title">
            <Icon type="person-stalker"></Icon>
            Contributors
          </li>
          <li v-for="user in Contributors" :key="user.name" class="footer-item">
            <a :href="user.profile" target="_blank">{{ user.name }}</a>
          </li>
        </ul>
      </Col>
      <Col :xs="24" :sm="12" :md="12" :lg="8">
        <ul class="footer-wrapper">
          <li class="footer-item title">
            <Icon type="chatboxes"></Icon>
            Contact
          </li>
          <li v-for="contact in contacts" :key="contact.type" class="footer-item">
            <a :href="contact.type" target="_blank">{{ contact.value }}</a>
          </li>
          <li class="footer-item copyright">
            <span>© LushX, Powered with ♥</span>
          </li>
        </ul>
      </Col>
    </Row>
  </footer>
</template>


<script>
  export default {
    name: "Footer",
    data() {
      return {
        repositories: [
          {
            name: 'LushX',
            url: '//github.com/LushX/LushX'
          }
        ],
        Contributors: [
          {
            name: 'BENULL',
            profile: '//github.com/BENULL'
          }, {
            name: 'zhenyaxu',
            profile: '//github.com/zhenyaxu'
          }, {
            name: 'Drohe',
            profile: '//github.com/Drohe'
          }, {
            name: 'Trevor',
            profile: '//github.com/HuangXiZhou'
          }
        ],
        contacts: [
          {
            type: 'mailto:ltobenull@gmail.com',
            value: 'ltobenull@gmail.com'
          }
        ]
      }
    }
  }
</script>


<style lang="less" scoped>
  footer {
    padding-left: 100px;
    padding-right: 100px;
    padding-bottom: 10px;
    width: 100%;
    min-height: 200px;
    background-color: #EDEEF1;
  }

  .footer-wrapper {
    padding-top: 16px;
    .title {
      font-size: 18px;
      font-weight: 500;
    }
    .footer-item {
      padding-bottom: 6px;
      & > a {
        font-size: 16px;
      }
    }
    .copyright {
      margin-top: 30px;
    }
  }
</style>
