# ice
安装：<br>
<b>第一步</b>，基于Windows下的安装，所以下载windows版的Ice:
http://www.zeroc.com/download.html<br>
      第二步，安装Ice：
常规安装即可，可以选择安装目录，本次安装是在G:\Ice<br>
      第三步，设置Ice的环境变量：
主要有环境变量需要设置：path、classpath、ICE_HOME<br>
例如：<br>
path:%ICE_HOME%\bin;<br>
classpath:%ICE_HOME%\Ice.jar;%ICE_HOME%\lib\db.jar;<br>
ICE_HOME:G:\Ice<br>
      第四步，检验：
在命令行中输入：icegridnode --version<br> 
如果现实当前安装的ice版本号，那么就说明安装成功。<br>
<br>
使用ice写一个脚本<br>
module demo { <br>
interface Printer {<br>
void printString(string s);  <br>
}; <br>
}; <br>
文件名为Printer.ice(可用记事本写，注意脚本的扩展名，将.txt去掉)<br>
然后运行cmd，切换到Printer.ice所在的目录，运行slice2java Printer.ice<br>
将demo文件夹中生成的类复制到项目中<br>

