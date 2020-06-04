export const myMixin = {
    data() {
        return {
            nextStep: false,
            notAcceptStep: false,
            scrollTop: 0,
            height: 0
        }
    },
    created() {

    },
    methods: {
        stage(value) {
            alert(value)
        },
        accept(value) {
        },
        getScroll() {
            this.scrollTop = this.scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || document.scrollingElement.scrollTop;
        
        },
        next(value) {
            this.nextStep = true;
            this.$emit('nextStep', 'this.nextStep');

        },
        notAccept(value) {
            this.notAcceptStep = true;
            this.$emit('getReject', 'this.notAcceptStep')

        }
    },
    mounted() {
        window.addEventListener("scroll", this.getScroll);
    },
}