<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />

<script type="text/javascript" src="/upload/swfupload.js"></script>
<script type="text/javascript" src="/upload/swfupload.queue.js"></script>
<script type="text/javascript" src="/upload/fileprogress.js"></script>
<script type="text/javascript" src="/upload/handlers.js"></script>
<script type="text/javascript">

    var swfu;

    function uploadSuccess(file,serverdata,resp) {
      var result = $.parseJSON(serverdata);
      if(result.code == 0){

      }
      else{
        alert(result.message);
      }
    }

    window.onload = function() {
        var settings = {
            flash_url : "/upload/swfupload.swf",
            file_post_name : "file",
        <#if format="1">
            post_params: {"USER" : "${user}"},
            upload_url: "/op/opimageupload/",
            file_types : "*.jpg;*.png;*.gif;*.jpeg",
            file_types_description : "JPG/PNG/GIF",
            file_size_limit : "10 MB",
            file_upload_limit : 100,
            file_queue_limit : 50,
        <#elseif format="2">
              post_params: {"USER" : "${user}"},
              upload_url: "/op/opfileupload/",
              file_types : "*.*",
              file_types_description : "Any Format",
              file_size_limit : "10 MB",
              file_upload_limit : 50,
              file_queue_limit : 50,
        <#elseif format="3">
          post_params: {"USER" : "${user}", "keepname": "${keepname}", "year": "${year}"},
          upload_url: "/op/opfileupload/",
          file_types : "*.*",
          file_types_description : "Any Format",
          file_size_limit : "10 MB",
          file_upload_limit : 50,
          file_queue_limit : 50,
        </#if>

            custom_settings : {
                progressTarget : "fsUploadProgress",
                cancelButtonId : "btnCancel"
            },
            debug: false,

            // Button settings
            button_image_url: "/upload/btnimage.png",
            button_width: "65",
            button_height: "29",
            button_placeholder_id: "spanButtonPlaceHolder",
            button_text: '<span class="theFont">上传</span>',
            button_text_style: ".theFont { font-size: 16; }",
            button_text_left_padding: 12,
            button_text_top_padding: 3,

            // The event handler functions are defined in handlers.js
            file_queued_handler : fileQueued,
            file_queue_error_handler : fileQueueError,
            file_dialog_complete_handler : fileDialogComplete,
            upload_start_handler : uploadStart,
            upload_progress_handler : uploadProgress,
            upload_error_handler : uploadError,
            upload_success_handler : uploadSuccess,
            upload_complete_handler : uploadComplete,
            queue_complete_handler : queueComplete	// Queue plugin event
        };

        swfu = new SWFUpload(settings);
    };
</script>
<style>


        /* -- Form Styles ------------------------------- */
    form {
        margin: 0;
        padding: 0;
    }

    div.fieldset {
        border:  1px solid #afe14c;
        margin: 10px 0;
        padding: 20px 10px;
    }
    div.fieldset span.legend {
        position: relative;
        background-color: #FFF;
        padding: 3px;
        top: -30px;
        font: 700 14px Arial, Helvetica, sans-serif;
        color: #73b304;
    }

    div.flash {
        width: 375px;
        margin: 10px 5px;
        border-color: #D9E4FF;

        -moz-border-radius-topleft : 5px;
        -webkit-border-top-left-radius : 5px;
        -moz-border-radius-topright : 5px;
        -webkit-border-top-right-radius : 5px;
        -moz-border-radius-bottomleft : 5px;
        -webkit-border-bottom-left-radius : 5px;
        -moz-border-radius-bottomright : 5px;
        -webkit-border-bottom-right-radius : 5px;

    }

    button,
    input,
    select,
    textarea {
        border-width: 1px;
        margin-bottom: 10px;
        padding: 2px 3px;
    }



    input[disabled]{ border: 1px solid #ccc } /* FF 2 Fix */


    label {
        width: 150px;
        text-align: right;
        display:block;
        margin-right: 5px;
    }

    #btnSubmit { margin: 0 0 0 155px ; }

        /* -- Table Styles ------------------------------- */
    td {
        font: 10pt Helvetica, Arial, sans-serif;
        vertical-align: top;
    }

    .progressWrapper {
        width: 357px;
        overflow: hidden;
    }

    .progressContainer {
        margin: 5px;
        padding: 4px;
        border: solid 1px #E8E8E8;
        background-color: #F7F7F7;
        overflow: hidden;
    }
        /* Message */
    .message {
        margin: 1em 0;
        padding: 10px 20px;
        border: solid 1px #FFDD99;
        background-color: #FFFFCC;
        overflow: hidden;
    }
        /* Error */
    .red {
        border: solid 1px #B50000;
        background-color: #FFEBEB;
    }

        /* Current */
    .green {
        border: solid 1px #DDF0DD;
        background-color: #EBFFEB;
    }

        /* Complete */
    .blue {
        border: solid 1px #CEE2F2;
        background-color: #F0F5FF;
    }

    .progressName {
        font-size: 8pt;
        font-weight: 700;
        color: #555;
        width: 323px;
        height: 14px;
        text-align: left;
        white-space: nowrap;
        overflow: hidden;
    }

    .progressBarInProgress,
    .progressBarComplete,
    .progressBarError {
        font-size: 0;
        width: 0%;
        height: 2px;
        background-color: blue;
        margin-top: 2px;
    }

    .progressBarComplete {
        width: 100%;
        background-color: green;
        visibility: hidden;
    }

    .progressBarError {
        width: 100%;
        background-color: red;
        visibility: hidden;
    }

    .progressBarStatus {
        margin-top: 2px;
        width: 337px;
        font-size: 7pt;
        font-family: Arial;
        text-align: left;
        white-space: nowrap;
    }

    a.progressCancel {
        font-size: 0;
        display: block;
        height: 14px;
        width: 14px;
        background-image: url(/upload/cancelbutton.gif);
        background-repeat: no-repeat;
        background-position: -14px 0px;
        float: right;
    }

    a.progressCancel:hover {
        background-position: 0px 0px;
    }


        /* -- SWFUpload Object Styles ------------------------------- */
    .swfupload {
        vertical-align: top;
    }

</style>
<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />

            <div class="span10">

                <legend>上传文件</legend>

                <fieldset>

                    <form id="form1" method="post" enctype="multipart/form-data">
                        <#if format="1">
                            <p>请选择文件进行上传(一次最多50个文件, jpg/png/gif, 最大10MB)</p>
                        <#elseif format="2">
                            <p>请选择文件进行上传(一次最多50个文件, 任何格式, 最大10MB)</p>
                        <#elseif format="3">
                          <p>请选择文件进行上传(一次最多50个文件, 任何格式, 最大10MB), <span class="text-warning">文件名只允许英文字母和数字</span></p>
                        </#if>

                        <div class="fieldset flash" id="fsUploadProgress">
                            <span class="legend">文件上传</span>
                        </div>
                        <div id="divStatus">0 文件已上传.</div>
                        <div>
                            <span id="spanButtonPlaceHolder"></span>
                            <input id="btnCancel" type="button" value="取消上传" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; font-size: 8pt; height: 29px;" />
                        </div>

                    </form>


                    <div class="controls">

                    </div>


                </fieldset>

            </div>
        </div>

    </div>
</div>


<#include "commons/footer.ftl" />