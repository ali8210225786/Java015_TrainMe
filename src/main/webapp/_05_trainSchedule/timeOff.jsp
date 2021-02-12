<!DOCTYPE html>
<html lang="en">
  <head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>課程管理</title>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      #tr_lesson_contrainer {
        border: 4px dashed rgb(16, 206, 165);
        border-radius: 20px;
        padding: 20px;
        margin: 10px;
      }

      #datebox {
        /* border: 1px solid red; */
        text-align: center;
      }

      .input_date {
        margin: 40px;
      }
      #showBtn,
      #saveBtu {
        width: 80px;
        height: 40px;
        margin: 20px;
        border-radius: 10px;
        outline: none;
        cursor: pointer;
      }
      #schedule {
        text-align: center;
      }

      table {
        border-collapse: collapse;
        border: 1px solid #ddd;
        margin: 0 auto;
      }

      th,
      td {
        border: 1px solid #ddd;
        width: 150px;
        height: 30px;
      }

      .is-closed {
        background: rgb(16, 206, 165);
      }
    </style>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
  </head>
  <body>
    <div id="tr_lesson_contrainer" x-data="courseData()" x-init="init($refs)">
      <h2>課程管理</h2>
      <div id="datebox">
        設定起始日期 :
        <input
          type="date"
          id="date_begin"
          name="date_begin"
          class="input_date"
          x-model="dateBegin"
          x-ref="date"
        />

        <br />
        設定每日開始時間 :
        <input
          type="number"
          name="time_begin"
          id="time_begin"
          min="0"
          max="24"
          class="input_date"
          x-model="timeBegin"
          @change="checkHours"
        />
        設定每日結束時間 :
        <input
          type="number"
          name="time_end"
          id="time_end"
          min="0"
          max="24"
          class="input_date"
          x-model="timeEnd"
          @change="checkHours"
        />
        <br />
        <button id="btn" :disabled="notAllowGenerate()" @click="produceDate($refs)">
          查詢
        </button>
      </div>

      <div id="schedule">
        <table>
          <thead id="title">
            <tr id="title_tr">
              <template x-for="date in dates" :key="date">
                <th x-text="date"></th>
              </template>
            </tr>
          </thead>
          <tbody id="tbody">
            <template x-for="hour in hours" :key="hour">
              <tr>
                <th x-text="hour + ':00'"></th>
                <template x-for="dateBody in dateBodys" :key="dateBody + hour">
                  <td
                    @click="closeToggle({dateBody,hour})"
                    :class="{'is-closed': isClosed( {dateBody, hour} ) }"
                  ></td>
                </template>
              </tr>
            </template>
          </tbody>
        </table>

        <button id="saveBtu" x-show="dates.length > 0" @click="save">確定儲存</button>
      </div>
    </div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://unpkg.com/dayjs@1.8.21/dayjs.min.js"></script>
    <script>
      function courseData() {
        return {
          dateBegin: '2020-02-10',
          dateEnd: null,
          timeBegin: 8,
          timeEnd: 12,
          dates: [],
          hours: [],
          dateBodys: [],
          closeHours: [],
          init($refs) {   //設定不可選擇當日之前日期
            const today = dayjs(new Date());
            todayStr = today.format("YYYY-MM-DD");
            // console.log($refs.date);
            $refs.date.min = todayStr;
          },
          notAllowGenerate() {   //判斷資料是否有輸入
            return (
              this.dateBegin === null ||
              this.timeBegin === null ||
              this.timeEnd === null
            );
          },
          produceDate() {    //產生行程表
            this.resetData();
            this.produceDateTitle();
            this.produceHourData();
          },
          resetData() {   //清空原有資料
            this.dates = [];
            this.hours = [];
            this.closeHours = [];
            this.dateBodys = [];
          },
          produceDateTitle() {   //產生日期列表
            const firstDay = dayjs(new Date(this.dateBegin));
            this.dateEnd = firstDay.add(7, "day").format("YYYY-MM-DD");
            this.dates.push("");
            for (let i = 0; i < 7; i++) {
              const date = firstDay.add(i, "day");
              const dateStr = date.format("YYYY-MM-DD");
              this.dates.push(dateStr);
              this.dateBodys.push(dateStr);
            }
          },
          produceHourData() {   //產生小時列表
            let firstTime = parseInt(this.timeBegin);
            let lastTime = parseInt(this.timeEnd);
            

            for (firstTime; firstTime <= lastTime; firstTime++) {           
              this.hours.push(firstTime);
            }

          },
          isClosed(closeHour) {   //判斷時段是否已經關閉
            const closeHourStr = closeHour.dateBody + "_" + closeHour.hour;
            return this.closeHours.indexOf(closeHourStr) > -1;
          },
          closeToggle(closeHour) {   //時段關閉開啟處理
            console.log(closeHour);
            const closeHourStr = closeHour.dateBody + "_" + closeHour.hour;
//             console.log(closeHourStr);
            const index = this.closeHours.indexOf(closeHourStr);
            if (index > -1) {
              this.closeHours.splice(index, 1);
            } else {
              this.closeHours.push(closeHourStr);
            }

//             console.log(this.closeHours);
          },
          checkHours() {  //檢查輸入的時段
            let firstTime = parseInt(this.timeBegin);
            let lastTime = parseInt(this.timeEnd);
            if(firstTime === null || lastTime === null){
            	return;
            }
            
            
            if(firstTime > lastTime){
               alert('結束時間不能比開始時間小唷~');
               return;
            }
             
          },
          save(){
        	  const hourData = this.closeHours.map(hour => hour);
        	  const payload = {
              closeHour: hourData,
              dateBegin: this.dateBegin,
              dateEnd: this.dateEnd
            };

        
            $.post("/trainme/TimeOff", {data:JSON.stringify(payload)},
              function (data, textStatus, jqXHR) {
                console.log(data);
              },
              "json"
            );
            



          }
        };
      }
    </script>
  </body>
</html>
