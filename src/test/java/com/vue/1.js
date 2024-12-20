import {createApp as _createApp} from "vue"
import {initCustomFormatter} from 'vue'

const _mount = () => {
  const AppComponent = __modules__["src/App.vue"].default
  AppComponent.name = 'Repl'
  const app = window.__app__ = _createApp(AppComponent)
  if (!app.config.hasOwnProperty('unwrapInjectedRef')) {
    app.config.unwrapInjectedRef = true
  }
  app.config.errorHandler = e => console.error(e)
  if (window.devtoolsFormatters) {
    const index = window.devtoolsFormatters.findIndex((v) => v.__vue_custom_formatter)
    window.devtoolsFormatters.splice(index, 1)
    initCustomFormatter()
  } else {
    initCustomFormatter()
  }
  app.mount('#app')
}
if (window.__ssr_promise__) {
  window.__ssr_promise__.then(_mount)
} else {
  _mount()
}
window.__next__()