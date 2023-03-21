import MainPage from "@/components/MainPage";
import StartPage from "@/components/StartPage";
import NotFoundError from "@/components/ErrorPage"
import {createRouter, createWebHistory} from "vue-router";

const routes = [
  {
    path: '/',
    name: 'start_page',
    component: StartPage,
    beforeEnter: (to, from, next) => {
      (localStorage.getItem("jwt") !== null) ? next({name: 'main_page'}) : next({name: 'auth-page'});
    }
  },
  {
    path: '/auth',
    name: 'auth-page',
    component: StartPage,

  },
  {
    path: '/main',
    name: 'main_page',
    component: MainPage,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("jwt")) next();
      else next({
        name: 'error-page-app',
      });
    }
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'error-page',
    component: NotFoundError,
    props: {
      default: true,
      errorCode: "404",
      errorMessage: "This page does not exist!"
    }
  },
  {
    path: '/error',
    name: 'error-page-app',
    component: NotFoundError,
    props: {
      default: true,
      errorCode: "401",
      errorMessage: "You do not have access to the main page. Please, Log in first!"
    }
  }

]

const router = createRouter({
  routes,
  history: createWebHistory(process.env.BASE_URL)
})

export default router;
