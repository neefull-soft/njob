package com.xxl.job.executor.service.jobhandler.config;

public enum MedicalDevicesType {

    MEDICAL_DEVICES_TYPE_RLJYBA("T_API_YLQX_RLJYBA"), MEDICAL_DEVICES_TYPE_YLSCBA("T_API_YLQX_YLSCBA"), MEDICAL_DEVICES_TYPE_JYXK("T_API_YLQX_JYXK"), MEDICAL_DEVICES_TYPE_SCXK("T_API_YLQX_SCXK"),MEDICAL_DEVICES_TYPE_WTSCBA("T_API_YLQX_WTSCBA");
    private final String name;

    private MedicalDevicesType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public enum RLJYBA {
        NEW("QX0E"), BG("QX0Y"), BF("QX0Z"), ZX("QX1A");
        private final String name;

        private RLJYBA(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum DataType {
        YLSCBA("SCBA"), RLJYBA("JYBA");
        private final String name;

        private DataType(String name) {
            this.name = name;
        }

        public String value() {
            return name;
        }
    }

    public enum JYXK {
        NEW("QX0M"), BG("QX0N"), YX("QX0P"), BF("QX1J"), ZX("QX0Q");
        private final String name;

        private JYXK(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum YLSCBA {
        NEW("QX0C"), BG("QX0W"), BF("QX1O"), ZX("QX1P");
        private final String name;

        private YLSCBA(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum SCXK {
        NEW("QX0G"), BG("QX0H"), YX("QX0I"), BF("QX0J"), ZX("QX0K");
        private final String name;

        private SCXK(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
