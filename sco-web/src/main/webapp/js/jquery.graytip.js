/**
 *
 */
(function ($) {
    // 插件的定义
    $.fn.graytip = function (options) {
        if (typeof options == "string") {
            options = {"msg":options};
        }
        // build main options before element iteration
        var opts = $.extend({}, $.fn.graytip.defaults, options);
        // iterate and reformat each matched element
        return this.each(function () {
            var $this = $(this);
            // build element specific options
            var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
            // update element styles

            var isCreate = true; //是否为第一个创建
            var tipFrameId = $this.attr("id") + "_tipFrame";
            var tipFrame; //浮动提示的jquery对象


            if (o.changeeable) { //是否有二次调用，修改参数
                tipFrame = $("#" + tipFrameId);
                if (tipFrame.length == 0) {
                    //创建浮动层
                    tipFrame = $("<div id=\"" + tipFrameId + "\"></div>");
                }
                else {
                    isCreate = false; //不是第一个创建
                    if (!options.msg) {  //没有定义msg, 以选中定义为准
                        o.msg = tipFrame.html();
                    }
                    if (!options.color) {  //没有定义color, 以选中定义为准
                        o.color = tipFrame.css("color");
                    }
                }
            }
            else {
                tipFrame = $("<div id=\"" + tipFrameId + "\"></div>");
            }


            setStyle($this, tipFrame, o);
            setData(tipFrame, o);

            if (isCreate) {
                $this.parent().append(tipFrame);

                //初始，如果已有值，则不显示
                if ($this.val() == "") {
                    showTipFrame($this, tipFrame, o);
                }
                else {
                    tipFrame.hide();
                }

                tipFrame.click(function () {
                    tipFrame.hide();
                    $this.focus();
                });

                $this.focus(function () {
                    tipFrame.hide();
                });

                $this.blur(function () {
                    if ($this.val() == "") {
                        showTipFrame($this, tipFrame, o);
                    }
                });

                //窗口事件
                $(window).resize(function () {
                    setStyle($this, tipFrame, o);
                });
            }

        });
    };


    function showTipFrame($text, $tip, o) {
        setStyle($text, $tip, o);
        $tip.show();
    }

    ;

    /**
     * 设置样式
     * @param $text
     * @param $tip
     * @param o 参数
     */
    function setStyle($text, $tip, o) {
        //定位，计算长宽
        var offset = $text.offset();
        var left = offset.left;
        var top = offset.top;
        var width = $text.width();
        var height = $text.height();

        //如果载体元素没有移动 或改变尺寸 则不作操作
        if (left != $tip.attr("pLeft") || top != $tip.attr("pTop") ||
            width != $tip.attr("pWidth") || height != $tip.attr("pHeight") || o.autoRefresh
            ) {

            $tip.attr("pLeft", left);
            $tip.attr("pTop", top);
            $tip.attr("pWidth", width);
            $tip.attr("pHeight", height);


            var border_top_width = $text.css("border-top-width");
            var border_left_width = $text.css("border-left-width");
            var border_bottom_width = $text.css("border-bottom-width");
            var border_right_width = $text.css("border-right-width");
            border_top_width = parseInt(border_top_width.substr(0, border_top_width.length - 2));
            border_left_width = parseInt(border_left_width.substr(0, border_left_width.length - 2));
            border_bottom_width = parseInt(border_bottom_width.substr(0, border_bottom_width.length - 2));
            border_right_width = parseInt(border_right_width.substr(0, border_right_width.length - 2));
            top = top + border_top_width;
            left = left + border_left_width;
            width = width - border_left_width;// - border_right_width;
            height = height - border_top_width;// - border_bottom_width;


            //边框位置
            var padding_top = $text.css("padding-top");
            var padding_left = $text.css("padding-left");

            var margin_top = $text.css("margin-top");
            var margin_left = $text.css("margin-left");

            //字体
            var font_style = $text.css("font-style");
            var font_variant = $text.css("font-variant");
            var font_weight = $text.css("font-weight");
            var font_size = $text.css("font-size");
            var line_height = $text.css("line-height");
            var font_family = $text.css("font-family");


            //背景
            $tip.css("background", "transparent");

            $tip.css("padding", "0px");
            $tip.css("padding-top", padding_top);
            $tip.css("padding-left", padding_left);
            $tip.css("margin", "0px");
            $tip.css("margin-top", margin_top);
            $tip.css("margin-left", margin_left);

            $tip.css("border-width", "0px");

            $tip.css("position", "absolute");
            $tip.css("top", top + "px");
            $tip.css("left", left + "px");
            $tip.css("width", width + "px");
            $tip.css("height", height + "px");

            //字体
            $tip.css("font-style", font_style);
            $tip.css("font-variant", font_variant);
            $tip.css("font-weight", font_weight);
            $tip.css("font-size", font_size);
            $tip.css("line-height", line_height);
            $tip.css("font-family", font_family);
        }

    }
	

    /**
     * 设置值
     * @param $tip
     * @param o
     */
    function setData($tip, o) {
        $tip.css("color", o.color);
        $tip.html(o.msg);
        if (o.hasTitle) {
            $tip.attr("title", o.msg);
        }
    }

    // 插件的defaults
    $.fn.graytip.defaults = {
        hasTitle:false,
        changeeable:true, //是否可以修改
        autoRefresh:false, //无论什么情况，每次显示时，都要刷新样式
        color:'#cccccc',
        msg:'请输入您的用户名'
    };
})(jQuery);