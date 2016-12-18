#pragma version(1)
#pragma rs java_package_name(com.asso.echopen.gpuchallenge)

uint32_t pixelsCount = 262144;
uint32_t numLines = 128;
int32_t *envelope_data;
int32_t *index_samp_line;
int32_t *image_index;
double *weight_coef;

void set_PixelsCount(int val) {
    // more processing in real life
    rsDebug("===========pixelsCount==================", val);
    rsDebug("===========EnvelopeData==================", envelope_data);
    pixelsCount = (uint32_t) val;
}

void set_NumLines(int val) {
    // more processing in real life
    rsDebug("===========numLines==================", val);
    numLines = (uint32_t) val;
}

void print_EnvelopeData() {
    // more processing in real life
    //rsDebug("===========EnvelopeData==================", envelope_data);
    //envelope_data = val;
}