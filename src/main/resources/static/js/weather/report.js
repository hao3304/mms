/**
 * Created by jack on 2018/2/21.
 */


$(function () {
    var $target = $('#select');
    $target.on('change',function (e) {
       var cityId =  $target.val();

       var url = '/report/cityId/' + cityId;
       window.location.href = url;
    })
})