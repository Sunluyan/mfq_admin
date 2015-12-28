/**
 * Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';
    config.skin='sj';
    config.dialog_backgroundCoverColor = '#000';
    config.dialog_backgroundCoverOpacity = 0.5;


    config.plugins =
        'basicstyles,' +
            'floatingspace,' +
            'font,' +
            'format,' +
            'pastefromword,' +
            'pastetext,' +
            'save,' +
            'tab,' +
            'toolbar,' +
            'undo,' +
            'maximize,' +
            'sourcearea,'+
            'indent,list,link,'+
            'colorbutton,' +
            'colordialog,' +
            'wysiwygarea';
};
