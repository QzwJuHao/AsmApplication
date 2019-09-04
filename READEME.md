    //判断是否是快速点击的事件
    public static boolean isDoubleClick(View v) {
        Object tag = v.getTag(v.getId());
        long beforeTimemiles = tag != null ? (long) tag : 0;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        v.setTag(v.getId(), timeInMillis);
        return timeInMillis - beforeTimemiles < FAST_CLICK_DELAY_TIME;
    }

if (isDoubleClick(view)) {
            return;
        }