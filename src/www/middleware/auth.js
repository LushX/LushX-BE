import storage from 'store'

export default function ({ store, redirect, route }) {
  if(route.fullPath === '/user' || route.fullPath === '/user/setting') {
    if (!storage.get('Authorization')) {
      return redirect('/')
    }
  }
}
