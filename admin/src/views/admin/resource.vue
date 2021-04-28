<template>
  <div>
    <p>
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <div class="row">
      <div class="col-md-6">
        <textarea id="resource-textarea" class="form-control" v-model="resourceStr" name="resource" rows="10"></textarea>

        <br>
        <button id="save-btn" type="button" class="btn btn-primary" v-on:click="save()">
          保存
        </button>
      </div>
      <div class="col-md-6">
<!--        <ul id="tree" class="ztree"></ul>-->
      </div>
    </div>

    <hr>

    <pagination ref="pagination" v-bind:list="list"></pagination>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
                    <th>id</th>
            <th>名称</th>
            <th>页面</th>
            <th>请求</th>
            <th>父id</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="resource in resources">
            <td>{{resource.id}}</td>
            <td>{{resource.name}}</td>
            <td>{{resource.page}}</td>
            <td>{{resource.request}}</td>
            <td>{{resource.parent}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(resource)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(resource.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";

  export default {
    name: 'system-resource',
    components: {Pagination},
    data: function () {
      return {
        resource: {},
        resources: [],
        resourceStr: "",
      }
    },
    mounted: function () {
      let _this = this;
      _this.$refs.pagination.size = 5;
      // 页面初始化之后就自动去执行下面的list方法
      _this.list(1);
      // this.$parent.activeSidebar("system-resource-sidebar");
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
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();
          let resp = response.data;
          _this.resources = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      /**
       * 点击保存
       */
      save() {
        let _this = this;

        // 保存校验
        if (Tool.isEmpty(_this.resourceStr)) {
          Toast.warning("资源不能为空！");
          return;
        }
        let json = JSON.parse(_this.resourceStr);

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save', json).then((response) => {
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("保存成功！");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除资源后不可恢复，确认删除？", function (){
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/resource/delete/' + id).then((response) => {
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        })
      }
    }
  }
</script>