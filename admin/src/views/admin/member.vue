<template>
  <div>
    <p>
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list"></pagination>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>id</th>
        <th>手机号</th>
        <th>密码</th>
        <th>昵称</th>
        <th>头像url</th>
        <th>注册时间</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="member in members">
            <td>{{member.id}}</td>
            <td>{{member.mobile}}</td>
            <td>{{member.password}}</td>
            <td>{{member.name}}</td>
            <td>{{member.photo}}</td>
            <td>{{member.registerTime}}</td>

      </tr>
      </tbody>
    </table>


  </div>
</template>

<script>
  import Pagination from "../../components/pagination";

  export default {
    name: 'business-member',
    components: {Pagination},
    data: function () {
      return {
        member: {},
        members: [],

      }
    },
    mounted: function () {
      let _this = this;
      _this.$refs.pagination.size = 5;
      // 页面初始化之后就自动去执行下面的list方法
      _this.list(1);
      // this.$parent.activeSidebar("business-member-sidebar");
    },
    methods: {


      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        // 向接口做了一个list请求
        // 这个是从前端localhost访问127.0.0.1的后端访问路径，会出现跨域问题，所以这里需要解决跨域问题
        // 这里请求直接到路由网关中，再由网关进行转发
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/member/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();
          let resp = response.data;
          _this.members = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>