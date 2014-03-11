package com.java.app.mustardseed.pdf;


import com.java.app.mustardseed.R;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

/**
 * Created by danscott on 18/02/2014.
 *
 * See PdfViewerActivity Android Project on Github to view source code
 * of PdfViewerActivity.
 *
 */
public class PdfView extends PdfViewerActivity {

    @Override
    public int getPreviousPageImageResource() {
        return R.drawable.left_arrow;
    }

    @Override
    public int getNextPageImageResource() {
        return R.drawable.right_arrow;
    }

    @Override
    public int getZoomInImageResource() {
        return R.drawable.zoom_in;
    }

    @Override
    public int getZoomOutImageResource() {
        return R.drawable.zoom_out;
    }

    @Override
    public int getPdfPasswordLayoutResource() {
        return R.layout.pdf_file_password;
    }

    @Override
    public int getPdfPageNumberResource() {
        return R.layout.dialog_pagenumber;
    }

    public int getPdfPasswordEditField() {
        return R.id.etPassword;
    }

    public int getPdfPasswordOkButton() {
        return R.id.btOK;
    }

    public int getPdfPasswordExitButton() {
        return R.id.btExit;
    }

    public int getPdfPageNumberEditField() {
        return R.id.pagenum_edit;
    }
}
