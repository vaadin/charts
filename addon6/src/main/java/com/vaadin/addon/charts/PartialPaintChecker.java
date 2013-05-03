package com.vaadin.addon.charts;

import com.vaadin.ui.Component;

/**
 **/
class PartialPaintChecker {
    private int dirtyFlags;
    private boolean partialRepaint = true;
    private boolean fullRepaint = true;
    private Component p;

    /**
     * <p>
     * Construct the checker only for this paintable.
     * </p>
     * 
     * @param paintable
     *            dedicated paintable
     **/
    public PartialPaintChecker(final Component paintable) {
        p = paintable;
    }

    /**
     * <p>
     * Check if a partial repaint is needed instead of full repaint.
     * </p>
     * 
     * @return false if full repaint is required or no flag was marked as dirty,
     *         true otherwise
     **/
    public boolean isPartialRepaint() {
        if (fullRepaint) {
            return false;
        }
        return dirtyFlags > 0;
    }

    /**
     * <p>
     * This method is meant to be called inside overridden paintContent() method
     * to determine if a flag is marked as dirty to send relevant update to
     * client. Developer can rely on this method to decide when to send updates
     * for relevant data.
     * </p>
     * 
     * @param flag
     *            flag to check
     * @return false if the flag was not marked as dirty, true otherwise (i.e.
     *         full repaint is needed or no flag was marked as dirty)
     **/
    public boolean isDirty(final int flag) {
        if (fullRepaint || dirtyFlags == 0) {
            return true;
        }
        return (dirtyFlags & flag) > 0;
    }

    /**
     * <p>
     * Indicate if a flag is dirty and needs to do a partial repaint. If a full
     * repaint is not in progress, {@link #partialPaint()} will be triggered and
     * eventually call {@link Paintable#requestRepaint()}.
     * </p>
     * <p>
     * Developer may explicitly call {@link Paintable#requestRepaint()} after
     * calling this method to enforce a full repaint.
     * </p>
     * 
     * @param flag
     *            flag to set as dirty, flags are stored using bit-comparison so
     *            ensure its value is a binary-bit like: 1, 2, 4, 8, 16, 32, and
     *            so on
     **/
    public void setDirty(final int flag) {
        if (!fullRepaint) {
            dirtyFlags |= flag;
            partialPaint();
        }
    }

    /**
     * <p>
     * The paintable must override paintContent() method and call this method at
     * very end of the method.
     * </p>
     **/
    public void paintContentPerformed() {
        clearDirtyFlags();
        fullRepaint = false;
    }

    /**
     * <p>
     * The paintable must override {@link Paintable#requestRepaint()} method and
     * call this method at very beginning of the method.
     * </p>
     **/
    public void checkBeforeRequestRepaint() {
        if (!partialRepaint) {
            clearDirtyFlags();
            fullRepaint = true;
        }
    }

    private void clearDirtyFlags() {
        dirtyFlags = 0;
    }

    private void partialPaint() {
        partialRepaint = true;
        try {
            p.requestRepaint();
        } finally {
            partialRepaint = false;
        }
    }
}