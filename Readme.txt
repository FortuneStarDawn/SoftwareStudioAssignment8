我基本上是照著sample打的，

打開server後，再啟動APP，一開始會到輸入ip的page，

如果輸入和server上所顯示的位置一致，則可以進入計算的頁面。

然後接下來就是在計算和答案兩個頁面來回跑動了。

每次按下計算時，就會更新Server的TextArea，顯示出計算的答案。

我的Server寫的比較簡單，只會接受一個連線，

連線成功後就進入迴圈，持續讀取Client做了怎樣的計算。

Client也很簡單，連線成功後，就將outputStream存起來，

之後每次觸發計算時，就把結果丟給outputStream然後write給Server。