import React, {Component} from 'react';
import {Alert, Button, StyleSheet, View} from 'react-native';

export default class ClicksMaker extends Component {

    _onPressButton() {
        fetch('https://request-counter.appspot.com/api/increment')
            .then((response) => response.json())
            .then((responseJson) => {
                Alert.alert('Click registered')
            })
            .catch((error) => {
                console.error(error);
            });
    }

    render() {
        return (
            <View style={styles.container}>
                <View style={styles.buttonContainer}>
                    <Button
                        onPress={this._onPressButton}
                        title="Add click"
                    />
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
    },
    buttonContainer: {
        margin: 20
    },
    alternativeLayoutButtonContainer: {
        margin: 20,
        flexDirection: 'row',
        justifyContent: 'space-between'
    }
})
