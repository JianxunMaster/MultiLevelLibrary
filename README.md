# MultiLevelLibrary  
## ※github新手上路，如果不能下载，请加群讨论更新。
## ※新添加multilevellist-v1.0.jar可直接使用（包含treenode和multilevel）
## 思路
普通列表：ListView适配器包含四个重写方法。其中，getCount指列表长度或，getView指item的布局和显示，一般列表写好这两个就ok。  
多级列表：数据对象一般是大数据集合，大数据包含小数据集合，小数据包含小小数据集合···  一般情况的处理方式有这几种：  
### 1）使用二级列表，子项通过addView的方式添加三级数据布局
（缺点：此方法每次滑动都要重新加载布局，影响性能，且只适用于3级列表，越多级越难写。）  
### 2）使用二级列表，子项设置子列表
（缺点：二级列表嵌套列表，增加了数据处理难度，适配器难设置，二级列表设置易出问题，且只适用于3级列表）  
### 3）使用一级列表，树节点结构模式，通过对象父类、对象子集、对象层级设置
（优点：可适用于多级列表，适配器易于维护，数据结构清晰。缺点：数据结构需要指定类型，如：父类id，自己id。）网上有封装好的库，比较好用，数据结构合适的推荐使用。  
### 4）使用一级列表：数据嵌套计算结构模式，通过计算设置getcount和getView对应对象进行适配
（优点：数据无需处理，操作简单。缺点：计算复杂）本文强烈推荐使用，已经封装，可直接使用。
## 对比：
1和2都差不多，不好用。3和4高大上，3需要满足树节点：有父类id和自己id，4需要满足嵌套，比较通用（一般数据都是嵌套，除非后台够叼，已经帮你算好）
## 总结：
简单的三级列表，无差别，推进使用4方法，操作简单。树节点模式使用3方法。嵌套结构使用4方法。
## (*╹▽╹*)：
担心算错？不用怕，已经算好了并封装。本文详细介绍4方法及使用。
## 先上图  
<img width="250" height="400" alt="截屏1" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/5B1D355AA5E1DAFFC1CDE98A9F9E0E59.jpg"/> <img width="250" height="400" alt="截屏2"
src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/329A5551F8D475A0BAF7F2036B85046B.jpg"/>

## 简单使用

### 1.引用依赖库

#### 1）源码下载

	下载压缩包解压到本地，导入开发工具
	
#### 2）快速导入
[![](https://jitpack.io/v/JianxunMaster/MultiLevelLibrary.svg)](https://jitpack.io/#JianxunMaster/MultiLevelLibrary)
##### 1.2.1 在project的build.gradle文件中添加
	
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
##### 1.2.2 在module的build.gradle中添加version以最新版本为准，例如当前version为1.0.2
	
	dependencies {
	        compile 'com.github.JianxunMaster:MultiLevelLibrary:version'
	}
	
### 2.在xml中使用ListView 

	v1.0.0版还没有RecyclerView的适配  			  

### 3.数据bean继承MultiLevelModel使得相互嵌套  
  
	public class ClassA extends MultiLevelModel	  

### 4.适配器继承MultiLevelAdapter重写onCreateView方法  
	 
	public class MultiLevelTestAdapter extends MultiLevelAdapter
	 

### 5.设置适配器
  
        adapter = new MultiLevelTestAdapter(this, true, false
                , 1);
        adapter.setOnMultiLevelListener(new MultiLevelAdapter.OnMultiLevelListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id
                    , MultiLevelModel current, MultiLevelModel max) {
                Toast.makeText(MainActivity.this, "position = " + position + "" +
                                " , current level = " + current.getLevel() + " , outside level = "
                                + max.getLevel()
                        , Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(adapter);
        listView.setAdapter(adapter);
        adapter.setList(list);
	
### 6.数据结构嵌套 

#### 1）继承MultiLevelModel,添加泛型，字段赋值使用@JSONField(name = "json字段")<建议使用fastjson可以快速解析，把json字段解析成children字段>
	
	public class Model extends MultiLevelModel<子集泛型>{
		@JSONField(name = "json字段名")
    		@Override
    		public void setChildren(List<子集泛型> children) {
        		super.setChildren(children);
    		}
	}
	
	
#### 2）示例数据如下：
   
	for (int i = 0; i < 3; i++) {
            ClassA classA = new ClassA(i, " A" + i);
            classA.setChildren(new ArrayList());
            list.add(classA);
            count += 1;
            for (int j = 0; j < 3; j++) {
                ClassB classB = new ClassB(j, " A" + i + " B" + j);
                classA.getChildren().add(classB);
                count += 1;
                for (int k = 0; k < 3; k++) {
                    ClassC classC = new ClassC(" A" + i + " B" + j + " C" + k);
                    classB.getChildren().add(classC);
                    count += 1;
                    for (int l = 0; l < 3; l++) {
                        ClassD classD = new ClassD(" A" + i + " B" + j + " C" + k + " D" + l
                                , "D");
                        classC.getChildren().add(classD);
                        count += 1;
                    }
                }
            }
        }
## 已添加jar包multilevellist-v1.0.jar，可出来treenode和multilevel两种数据格式

## QQ群464635057 欢迎加群讨论 扫码加群，群文件有jar包，jar包包含treenode
<div align=center><img alt="群二维码" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/MultiLevelLibrary%E8%AE%A8%E8%AE%BA%E7%BE%A4%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png"/></div>

## 大哥大姐觉得好用的打点赏，一点心意作鼓励

<div align=center>
<img width="200" height="300" alt="微信扫码打赏" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/C1D99830DC2142387181396BF25B1C59.png"/>
<img width="200" height="300" alt="支付宝扫码打赏" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/80377AD8D54B09D5574FFF0DA75B61F7.png"/>  
</div>

## 此库持续更新中···加油~~~~(>_<)~~~~
