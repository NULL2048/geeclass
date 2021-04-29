<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
              Dropdown
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>
  </header>
</template>

<script>

  // import TheLogin from "./login";
  export default {
    name: 'theHeader',
    // components: {TheLogin},
    // data: function () {
    //   return {
    //     loginMember: {}
    //   }
    // },
    // mounted() {
    //   let _this = this;
    //   _this.loginMember = Tool.getLoginMember();
    // },
    // methods: {
    //   /**
    //    * 打开登录注册窗口
    //    */
    //   openLoginModal() {
    //     let _this = this;
    //     _this.$refs.loginComponent.openLoginModal();
    //   },
    //
    //   setLoginMember(loginMember) {
    //     let _this = this;
    //     _this.loginMember = loginMember;
    //   },
    //
    //   logout () {
    //     let _this = this;
    //     _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/member/logout/' + _this.loginMember.token).then((response)=>{
    //       let resp = response.data;
    //       if (resp.success) {
    //         Tool.setLoginMember(null);
    //         _this.loginMember = {};
    //         Toast.success("退出登录成功");
    //         _this.$router.push("/");
    //       } else {
    //         Toast.warning(resp.message);
    //       }
    //     });
    //   },
    //
    // }
  }
</script>
