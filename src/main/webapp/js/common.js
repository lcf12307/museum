//页面加载或ifame刷新时跳转高度
$(window).on('load', function() {
    resize();
}).resize();

function resize() {
    var $content = $('.content');
    $content.height($(this).height() - 120);
    //console.log("log",$(this));
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}
