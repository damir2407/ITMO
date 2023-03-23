import MainPage from "@/components/MainPage";
import NotFoundError from "@/components/ErrorPage"
import {createRouter, createWebHistory} from "vue-router";
import AboutUs from "@/components/AboutUs";
import Shop from "@/components/Shop";
import Profile from "@/components/Profile";
import UploadGame from "@/components/UploadGame";
import Game from "@/components/Game";
import Wallet from "@/components/Wallet";
import Library from "@/components/Library";
import Inventory from "@/components/Inventory";
import Guides from "@/components/Guides";
import TradingFloor from "@/components/TradingFloor";

const routes = [
  {
    path: '/',
    name: 'main_page',
    component: MainPage,
  },

  {
    path: '/trade',
    name: 'trading_floor',
    component: TradingFloor,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("role") !== "ROLE_DEV") next();
      else next({
        name: 'main_page',
      });
    }
  },

  {
    path: '/wallet',
    name: 'wallet',
    component: Wallet,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("jwt") && (localStorage.getItem("role") === "ROLE_USER")) next();
      else next({
        name: 'main_page',
      });
    }
  },


  {
    path: '/inventory',
    name: 'inventory',
    component: Inventory,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("jwt") && (localStorage.getItem("role") === "ROLE_USER")) next();
      else next({
        name: 'main_page',
      });
    }
  },

  {
    path: '/shop',
    name: 'shop',
    component: Shop,
  },
  {
    path: '/guides',
    name: 'guides',
    component: Guides,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("role") !== "ROLE_DEV") next();
      else next({
        name: 'main_page',
      });
    }
  },
  {
    path: '/profile/:login',
    name: 'profile',
    component: Profile,
    props: true
  },


  {
    path: '/game/:game_name',
    name: 'game',
    component: Game,
    props: true
  },
  {
    path: '/game_upload',
    name: 'upload_game',
    component: UploadGame,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("jwt") && (localStorage.getItem("role") === "ROLE_DEV")) next();
      else next({
        name: 'main_page',
      });
    }
  },
  {
    path: '/about_us',
    name: 'about_us',
    component: AboutUs
  },


  {
    path: '/library',
    ame: 'library',
    component: Library,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("jwt") && (localStorage.getItem("role") === "ROLE_USER")) next();
      else next({
        name: 'main_page',
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
