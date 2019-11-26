package com.bfby.coldchain.Bean;

import java.io.Serializable;
import java.util.List;

public class VehicleType implements Serializable {


    /**
     * status : 1
     * rows : [{"Id":"b51744c1-6d17-476c-8d4a-cd222cc1284e","Text":"普通货车"},{"Id":"ffd504b9-1649-4eff-a2ec-4a7e799bb82c","Text":"厢式货车"},{"Id":"029f992c-8ce0-4361-8850-3475a814f93c","Text":"罐式货车"},{"Id":"3a76e37d-94ea-42c7-8a4d-168c59c40896","Text":"牵引车"},{"Id":"16f9da7c-b48f-4f6a-8892-86a0345fa5fe","Text":"普通挂车"},{"Id":"efb75c49-41fb-4fbf-a90f-074ef4df4dcd","Text":"罐式挂车"},{"Id":"05d1617f-ac62-4385-bd3d-40551dc72ed0","Text":"集装箱挂车"},{"Id":"bb9e071b-59fa-4916-8641-2439307d3056","Text":"仓栅货车"},{"Id":"35e60912-b0fc-442a-8dfb-b94567f4a01b","Text":"封闭货车"},{"Id":"d9b206b5-e1ac-4502-acea-c84b5706592b","Text":"平板货车"},{"Id":"e4224f04-dee7-4cf8-8729-d39b1bd4e5fe","Text":"集装箱车"},{"Id":"ec5ef26a-2fb2-4e18-ab41-a655d319f225","Text":"自卸货车"},{"Id":"27e1694c-4f7e-459d-9699-5f3a27cedf8b","Text":"特殊结构货车"},{"Id":"3d938dc8-2eda-4000-aa35-ab093e5db86b","Text":"专项作业车"},{"Id":"7eff6281-2552-462d-a0cb-692d85fd16d9","Text":"厢式挂车"},{"Id":"b1b7c66c-b5fe-410f-b3b7-7b911d8eb3a2","Text":"仓栅式挂车"},{"Id":"c8cc0b38-9650-478c-a40e-d0a7f15aaeea","Text":"平板挂车"},{"Id":"2ffd7013-527e-4a5e-bb17-62567e91b6c2","Text":"自卸挂车"},{"Id":"e6c2ac66-8ba2-44f5-95ff-a00f72a0cbf0","Text":"专项作业挂车"},{"Id":"14cfbed3-4db6-42c7-8832-077e2c80bf1b","Text":"车辆运输车"},{"Id":"5998d822-dd16-4550-a28d-6f86e20e8e45","Text":"车辆运输车(单排)"}]
     */

    private int status;
    private List<RowsBean> rows;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * Id : b51744c1-6d17-476c-8d4a-cd222cc1284e
         * Text : 普通货车
         */

        private String Id;
        private String Text;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }
    }
}
