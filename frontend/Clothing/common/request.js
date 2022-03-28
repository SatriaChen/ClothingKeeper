import baseurl from'../common/config.js'

 const request = function(options) {
     options.url = baseurl + options.url;
     try {
       const token = uni.getStorageSync('token');
       const userid = uni.getStorageSync('userid');
       debugger
       if (token) {
         options.header = {
           'Authorization' :  token
         };
         console.log(token);
        }else{
            uni.navigateTo({
                url: 'pages/login/login' 
            });
            return;
        }
     } catch (err) {
      console.log(err)
    }
    return uni.request(options);
  }
export default request;