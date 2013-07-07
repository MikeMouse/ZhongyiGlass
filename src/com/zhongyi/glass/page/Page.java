package com.zhongyi.glass.page;

public class Page {

	/** 当前页 */
	private int pageNo;

	/** 每页数据量 */
	private int pageSize;

	/** 总数据量 */
	private int allCount;

	/** 总页数 */
	private int allPage;

	/** 每页开始 */
	private int recordStart;

	/** 每页结束 */
	private int recordEnd;

	/** 是否有上一页 */
	private boolean hasPrePage;

	/** 是否有下一页 */
	private boolean hasNextPage;

	/** 显示页数 */
	private int showCount;

	/** 显示开始 */
	private int showStart;

	/** 显示结束 */
	private int showEnd;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getRecordStart() {
		return recordStart;
	}

	public void setRecordStart(int recordStart) {
		this.recordStart = recordStart;
	}

	public int getRecordEnd() {
		return recordEnd;
	}

	public void setRecordEnd(int recordEnd) {
		this.recordEnd = recordEnd;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getShowStart() {
		return showStart;
	}

	public void setShowStart(int showStart) {
		this.showStart = showStart;
	}

	public int getShowEnd() {
		return showEnd;
	}

	public void setShowEnd(int showEnd) {
		this.showEnd = showEnd;
	}

	public Page() {
		pageNo = 1;
		pageSize = 0;
		allPage = 1;
		recordStart = 0;
		recordEnd = 0;
		hasPrePage = false;
		hasNextPage = false;
		try {
			excecute();
		} catch (Exception exception) {
		}
	}

	public Page(int pageNo, int allCount) {
		this.pageNo = pageNo;
		this.pageSize = 10;
		this.allCount = allCount;
		this.allPage = 1;
		this.recordStart = 0;
		this.recordEnd = 0;
		hasPrePage = false;
		hasNextPage = false;
		try {
			excecute();
		} catch (Exception exception) {
		}
	}

	public Page(int pageNo, int pageSize, int allCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.allCount = allCount;
		this.allPage = 1;
		this.recordStart = 0;
		this.recordEnd = 0;
		hasPrePage = false;
		hasNextPage = false;
		try {
			excecute();
		} catch (Exception exception) {
		}
	}

	public void excecute() {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		recordStart = (pageNo - 1) * pageSize + 1;
		recordEnd = Math.min(recordStart + pageSize, allCount);
		if (allCount % pageSize == 0) {
			allPage = allCount / pageSize;
		} else {
			allPage = allCount / pageSize + 1;
		}
		if (pageNo > 1) {
			hasPrePage = true;
		}
		if (pageNo < allPage) {
			hasNextPage = true;
		}
		if (showCount <= 0) {
			showCount = 9;
		}

		// 设定显示N页,我设定为9个;看效果图
		showCount = Math.min(showCount, allPage);
		if (showCount < 9) {
			showStart = 1;
			showEnd = showCount + showStart;
		} else {
			if (pageNo - 3 <= 1) {
				showStart = 1;
			} else {
				showStart = pageNo - 3;
			}
			if (allPage - 3 < pageNo) {
				showEnd = allPage - 3;
			} else {
				if (showStart == 1) {
					showEnd = showStart + 6;
				} else {
					showEnd = pageNo + 3;
				}
			}
		}
	}
}