<template>
  <header>
    <div class="collapse bg-dark" id="navbarHeader">
      <div class="container">
        <div class="row">
          <div class="col-sm-8 col-md-7 py-4">
            <h4 class="text-white">About1</h4>
            <p class="text-muted">Add some information about the album below, the author, or any other background context. Make it a few sentences long so folks can pick up some informative tidbits. Then, link them off to some social networking sites or contact information.</p>
          </div>
          <div class="col-sm-4 offset-md-1 py-4">
            <h4 class="text-white">Contact</h4>
            <ul class="list-unstyled">
              <li><a href="#" class="text-white">Follow on Twitter</a></li>
              <li><a href="#" class="text-white">Like on Facebook</a></li>
              <li><a href="#" class="text-white">Email me</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
      <div class="container d-flex justify-content-between">
        <a href="#" class="navbar-brand d-flex align-items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="mr-2" viewBox="0 0 24 24" focusable="false"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>
          <strong>Album</strong>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
    </div>
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
