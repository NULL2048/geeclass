Tool = {
  /**
   * 空校验 null或""都返回true
   */
  isEmpty: function (obj) {
    if ((typeof obj == 'string')) {
      return !obj || obj.replace(/\s+/g, "") == ""
    } else {
      return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
  },

  /**
   * 非空校验
   */
  isNotEmpty: function (obj) {
    return !this.isEmpty(obj);
  },

  /**
   * 长度校验
   */
  isLength: function (str, min, max) {
    return $.trim(str).length >= min && $.trim(str).length <= max;
  },

  /**
   * 时间格式化，date为空时取当前时间
   */
  dateFormat: function (format, date) {
    let result;
    if (!date) {
      date = new Date();
    }
    const option = {
      "y+": date.getFullYear().toString(),        // 年
      "M+": (date.getMonth() + 1).toString(),     // 月
      "d+": date.getDate().toString(),            // 日
      "h+": date.getHours().toString(),           // 时
      "m+": date.getMinutes().toString(),         // 分
      "s+": date.getSeconds().toString()          // 秒
    };
    for (let i in option) {
      result = new RegExp("(" + i + ")").exec(format);
      if (result) {
        format = format.replace(result[1], (result[1].length == 1) ? (option[i]) : (option[i].padStart(result[1].length, "0")))
      }
    }
    return format;
  },

  /**
   * 移除对象数组中的对象
   * @param array
   * @param obj
   * @returns {number}
   */
  removeObj: function (array, obj) {
    let index = -1;
    for (let i = 0; i < array.length; i++) {
      if (array[i] === obj) {
        array.splice(i, 1);
        index = i;
        break;
      }
    }
    return index;
  },

  /**
   * 10进制转62进制
   * @param number
   * @returns {string}
   * @private
   */
  _10to62: function (number) {
    let chars = '0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ';
    let radix = chars.length;
    let arr = [];
    do {
      let mod = number % radix;
      number = (number - mod) / radix;
      arr.unshift(chars[mod]);
    } while (number);
    return arr.join('');
  },

  /**
   * 保存登录用户信息
   */
  setLoginUser: function (loginUser) {
    SessionStorage.set(SESSION_KEY_LOGIN_USER, loginUser);
  },

  /**
   * 获取登录用户信息
   */
  getLoginUser: function () {
    return SessionStorage.get(SESSION_KEY_LOGIN_USER) || {};
  },

  /**
   * 随机生成[len]长度的[radix]进制数
   * @param len
   * @param radix 默认62
   * @returns {string}
   */
  uuid: function (len, radix) {
    let chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    let uuid = [];
    radix = radix || chars.length;

    for (let i = 0; i < len; i++) {
      uuid[i] = chars[0 | Math.random() * radix];
    }

    return uuid.join('');
  },

  /**
   * 查找是否有权限
   * @param id 资源id
   */
  hasResource: function (id) {
    let _this = this;
    // 获取当前登录用户所持有资源
    let resources = _this.getLoginUser().resources;
    // 如果该用户资源为空，直接返回false
    if (_this.isEmpty(resources)) {
      return false;
    }
    // 用当前操作所需要的资源id与该用户所持有的资源一一进行比对，如果有比对上的，证明这个用户有权限，直接返回true
    for (let i = 0; i < resources.length; i++) {
      if (id === resources[i].id) {
        return true;
      }
    }
    return false;
  },

  /**
   * 随机生成[len]长度的[radix]进制数
   * @param len
   * @param radix 默认62
   * @returns {string}
   */
  uuid: function (len, radix) {
    let chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    let uuid = [];
    radix = radix || chars.length;

    for (let i = 0; i < len; i++) {
      uuid[i] = chars[0 | Math.random() * radix];
    }

    return uuid.join('');
  }
};