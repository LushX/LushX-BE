import Vue from 'vue'

Vue.filter('sectionTitle', title => {
  switch (title) {
    case 'movies':
      return '电影'
      break

    case 'tv':
      return '电视剧'
      break

    case 'lives':
      return '直播'
      break

    case 'animation':
      return '动漫'
      break

    case 'cams':
      return '综艺'
      break

    default:
      break
  }
})
