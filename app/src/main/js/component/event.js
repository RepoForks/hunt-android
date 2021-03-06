/**
 * Created by daniel on 8/5/17.
 */
import React, {Component} from 'react';
import {StyleSheet, Text, View, Image, TouchableNativeFeedback} from 'react-native';
import {formatHour} from "../util/dateUtils"
import PropTypes from 'prop-types';

export default class EventItem extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        let imageUrl;
        if (this.props.event.guests[0]) {
            imageUrl = this.props.event.guests[0].image_url["64px"]
        }

        let displayStartedAt;
        let startAt = this.props.event.starts_at;
        if (startAt) {
            displayStartedAt = `Start at ${formatHour(startAt)}`
        }

        return (
            <TouchableNativeFeedback>
                <View style={styles.container}>
                    <View style={styles.avatarHolder}>
                        <Image style={styles.avatar}
                               source={{uri: imageUrl}}/>
                    </View>

                    <View style={{
                        flex: 1,
                        flexDirection: 'column',
                        marginLeft: 16
                    }}>

                        <Text style={styles.title}>
                            {this.props.event.name}
                        </Text>

                        <Text style={styles.tagline}>
                            {this.props.event.tagline}
                        </Text>

                        <Text style={styles.timeStamp}>
                            {displayStartedAt}
                        </Text>
                        <Text style={styles.info}>
                            {`${this.props.event.subscriber_count} attending`}
                        </Text>

                    </View>

                </View>
            </TouchableNativeFeedback>
        )
    }
}

EventItem.propTypes = {
    event: PropTypes.object,
};

const styles = StyleSheet.create({

    container: {
        flex: 1,
        flexDirection: 'row',
        backgroundColor: 'white',
        paddingTop: 8,
        paddingBottom: 8,
    },

    info: {
        marginTop: 2
    },

    tagline: {
        marginTop: 4
    },

    avatarHolder: {
        justifyContent: 'center',
        alignItems: 'center',
        height: 64,
        width: 64,
    },

    avatar: {
        borderRadius: 32,
        marginLeft: 16,
        resizeMode: 'contain',
        height: 64,
        width: 64,

    },

    title: {
        fontSize: 18,
        color: 'rgba(0, 0, 0, 0.87)'
    },

    timeStamp: {
        marginTop: 2,
        fontSize: 12,
        color: 'rgba(0, 0, 0, 0.26)'
    }
});